import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWeekMenu } from 'app/shared/model/week-menu.model';
import { WeekMenuService } from './week-menu.service';
import { WeekMenuDeleteDialogComponent } from './week-menu-delete-dialog.component';

@Component({
  selector: 'jhi-week-menu',
  templateUrl: './week-menu.component.html'
})
export class WeekMenuComponent implements OnInit, OnDestroy {
  weekMenus?: IWeekMenu[];
  eventSubscriber?: Subscription;

  constructor(protected weekMenuService: WeekMenuService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.weekMenuService.query().subscribe((res: HttpResponse<IWeekMenu[]>) => (this.weekMenus = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWeekMenus();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWeekMenu): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWeekMenus(): void {
    this.eventSubscriber = this.eventManager.subscribe('weekMenuListModification', () => this.loadAll());
  }

  delete(weekMenu: IWeekMenu): void {
    const modalRef = this.modalService.open(WeekMenuDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.weekMenu = weekMenu;
  }
}
