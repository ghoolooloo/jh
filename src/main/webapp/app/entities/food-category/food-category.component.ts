import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFoodCategory } from 'app/shared/model/food-category.model';
import { FoodCategoryService } from './food-category.service';
import { FoodCategoryDeleteDialogComponent } from './food-category-delete-dialog.component';

@Component({
  selector: 'jhi-food-category',
  templateUrl: './food-category.component.html'
})
export class FoodCategoryComponent implements OnInit, OnDestroy {
  foodCategories?: IFoodCategory[];
  eventSubscriber?: Subscription;

  constructor(
    protected foodCategoryService: FoodCategoryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.foodCategoryService.query().subscribe((res: HttpResponse<IFoodCategory[]>) => (this.foodCategories = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFoodCategories();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFoodCategory): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFoodCategories(): void {
    this.eventSubscriber = this.eventManager.subscribe('foodCategoryListModification', () => this.loadAll());
  }

  delete(foodCategory: IFoodCategory): void {
    const modalRef = this.modalService.open(FoodCategoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.foodCategory = foodCategory;
  }
}
