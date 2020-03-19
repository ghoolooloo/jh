import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWeekStock } from 'app/shared/model/week-stock.model';
import { WeekStockService } from './week-stock.service';

@Component({
  templateUrl: './week-stock-delete-dialog.component.html'
})
export class WeekStockDeleteDialogComponent {
  weekStock?: IWeekStock;

  constructor(protected weekStockService: WeekStockService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.weekStockService.delete(id).subscribe(() => {
      this.eventManager.broadcast('weekStockListModification');
      this.activeModal.close();
    });
  }
}
