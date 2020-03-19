import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFoodCategory } from 'app/shared/model/food-category.model';
import { FoodCategoryService } from './food-category.service';

@Component({
  templateUrl: './food-category-delete-dialog.component.html'
})
export class FoodCategoryDeleteDialogComponent {
  foodCategory?: IFoodCategory;

  constructor(
    protected foodCategoryService: FoodCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.foodCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('foodCategoryListModification');
      this.activeModal.close();
    });
  }
}
