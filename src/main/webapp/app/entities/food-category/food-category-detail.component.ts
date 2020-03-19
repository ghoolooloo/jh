import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFoodCategory } from 'app/shared/model/food-category.model';

@Component({
  selector: 'jhi-food-category-detail',
  templateUrl: './food-category-detail.component.html'
})
export class FoodCategoryDetailComponent implements OnInit {
  foodCategory: IFoodCategory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ foodCategory }) => (this.foodCategory = foodCategory));
  }

  previousState(): void {
    window.history.back();
  }
}
