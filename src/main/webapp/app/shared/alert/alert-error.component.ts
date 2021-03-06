import { Component, OnDestroy, TemplateRef, ViewChild } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';
import { JhiEventManager, JhiAlert, JhiAlertService, JhiEventWithContent } from 'ng-jhipster';
import { Subscription } from 'rxjs';

import { AlertError } from './alert-error.model';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'jhi-alert-error',
  template: `
    <ng-template let-alts="data">
      <div class="ant-notification-notice-content">
        <div class="ant-notification-notice-message" jhiTranslate="error.internalServerError">操作失败！</div>
        <div class="ant-notification-notice-description">
          <ul>
            <li *ngFor="let alt of alts">
              <span *ngIf="alt && alt.msg" [innerHTML]="alt.msg"></span>
            </li>
          </ul>
        </div>
      </div>
    </ng-template>
  `
})
export class AlertErrorComponent implements OnDestroy {
  alerts: JhiAlert[] = [];
  errorListener: Subscription;
  httpErrorListener: Subscription;

  @ViewChild(TemplateRef, { static: false })
  template!: TemplateRef<{}>;

  constructor(
    private alertService: JhiAlertService,
    private eventManager: JhiEventManager,
    private notificationService: NzNotificationService,
    translateService: TranslateService
  ) {
    this.errorListener = eventManager.subscribe('jhApp.error', (response: JhiEventWithContent<AlertError>) => {
      const errorResponse = response.content;
      this.addErrorAlert(errorResponse.message, errorResponse.key, errorResponse.params);
    });

    this.httpErrorListener = eventManager.subscribe('jhApp.httpError', (response: JhiEventWithContent<HttpErrorResponse>) => {
      const httpErrorResponse = response.content;
      switch (httpErrorResponse.status) {
        // connection refused, server not reachable
        case 0:
          this.addErrorAlert('Server not reachable', 'error.server.not.reachable');
          break;

        case 400: {
          const arr = httpErrorResponse.headers.keys();
          let errorHeader = null;
          let entityKey = null;
          arr.forEach(entry => {
            if (entry.toLowerCase().endsWith('app-error')) {
              errorHeader = httpErrorResponse.headers.get(entry);
            } else if (entry.toLowerCase().endsWith('app-params')) {
              entityKey = httpErrorResponse.headers.get(entry);
            }
          });
          if (errorHeader) {
            const entityName = translateService.instant('global.menu.entities.' + entityKey);
            this.addErrorAlert(errorHeader, errorHeader, { entityName });
          } else if (httpErrorResponse.error !== '' && httpErrorResponse.error.fieldErrors) {
            const fieldErrors = httpErrorResponse.error.fieldErrors;
            for (const fieldError of fieldErrors) {
              if (['Min', 'Max', 'DecimalMin', 'DecimalMax'].includes(fieldError.message)) {
                fieldError.message = 'Size';
              }
              // convert 'something[14].other[4].id' to 'something[].other[].id' so translations can be written to it
              const convertedField = fieldError.field.replace(/\[\d*\]/g, '[]');
              const fieldName = translateService.instant('jhApp.' + fieldError.objectName + '.' + convertedField);
              this.addErrorAlert('Error on field "' + fieldName + '"', 'error.' + fieldError.message, { fieldName });
            }
          } else if (httpErrorResponse.error !== '' && httpErrorResponse.error.message) {
            this.addErrorAlert(httpErrorResponse.error.message, httpErrorResponse.error.message, httpErrorResponse.error.params);
          } else {
            this.addErrorAlert(httpErrorResponse.error);
          }
          break;
        }

        case 404:
          this.addErrorAlert('Not found', 'error.url.not.found');
          break;

        default:
          if (httpErrorResponse.error !== '' && httpErrorResponse.error.message) {
            this.addErrorAlert(httpErrorResponse.error.message);
          } else {
            this.addErrorAlert(httpErrorResponse.error);
          }
      }
    });
  }

  setClasses(alert: JhiAlert): { [key: string]: boolean } {
    const classes = { 'jhi-toast': Boolean(alert.toast) };
    if (alert.position) {
      return { ...classes, [alert.position]: true };
    }
    return classes;
  }

  ngOnDestroy(): void {
    if (this.errorListener) {
      this.eventManager.destroy(this.errorListener);
    }
    if (this.httpErrorListener) {
      this.eventManager.destroy(this.httpErrorListener);
    }
  }

  addErrorAlert(message: string, key?: string, data?: any): void {
    message = key && key !== null ? key : message;

    const newAlert: JhiAlert = {
      type: 'danger',
      msg: message,
      params: data,
      timeout: 5000,
      toast: this.alertService.isToast(),
      scoped: true
    };

    this.alerts.push(this.alertService.addAlert(newAlert, this.alerts));
    this.notificationService.template(this.template, { nzData: this.alerts, nzPosition: 'bottomRight' });
  }
}
