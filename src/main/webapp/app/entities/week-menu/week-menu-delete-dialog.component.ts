import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWeekMenu } from 'app/shared/model/week-menu.model';
import { WeekMenuService } from './week-menu.service';

@Component({
  templateUrl: './week-menu-delete-dialog.component.html'
})
export class WeekMenuDeleteDialogComponent {
  weekMenu?: IWeekMenu;

  constructor(protected weekMenuService: WeekMenuService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.weekMenuService.delete(id).subscribe(() => {
      this.eventManager.broadcast('weekMenuListModification');
      this.activeModal.close();
    });
  }
}
