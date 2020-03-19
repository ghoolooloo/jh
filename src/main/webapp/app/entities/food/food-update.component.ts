import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFood, Food } from 'app/shared/model/food.model';
import { FoodService } from './food.service';
import { IFoodCategory } from 'app/shared/model/food-category.model';
import { FoodCategoryService } from 'app/entities/food-category/food-category.service';

@Component({
  selector: 'jhi-food-update',
  templateUrl: './food-update.component.html'
})
export class FoodUpdateComponent implements OnInit {
  isSaving = false;
  foodcategories: IFoodCategory[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(20)]],
    sn: [null, [Validators.required, Validators.maxLength(10)]],
    thumbnail: [null, [Validators.maxLength(200)]],
    picture: [null, [Validators.maxLength(200)]],
    price: [null, [Validators.required, Validators.min(1), Validators.max(999999)]],
    packingFee: [null, [Validators.min(0), Validators.max(999999)]],
    desc: [null, [Validators.maxLength(300)]],
    sort: [null, [Validators.required, Validators.min(0), Validators.max(999999999)]],
    feedingMode: [null, [Validators.required]],
    createdBy: [null, [Validators.required, Validators.maxLength(20)]],
    createdDate: [null, [Validators.required]],
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]],
    categoryId: []
  });

  constructor(
    protected foodService: FoodService,
    protected foodCategoryService: FoodCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ food }) => {
      if (!food.id) {
        const today = moment().startOf('day');
        food.createdDate = today;
        food.lastModifiedDate = today;
      }

      this.updateForm(food);

      this.foodCategoryService.query().subscribe((res: HttpResponse<IFoodCategory[]>) => (this.foodcategories = res.body || []));
    });
  }

  updateForm(food: IFood): void {
    this.editForm.patchValue({
      id: food.id,
      name: food.name,
      sn: food.sn,
      thumbnail: food.thumbnail,
      picture: food.picture,
      price: food.price,
      packingFee: food.packingFee,
      desc: food.desc,
      sort: food.sort,
      feedingMode: food.feedingMode,
      createdBy: food.createdBy,
      createdDate: food.createdDate ? food.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: food.lastModifiedDate ? food.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: food.lastModifiedBy,
      categoryId: food.categoryId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const food = this.createFromForm();
    if (food.id !== undefined) {
      this.subscribeToSaveResponse(this.foodService.update(food));
    } else {
      this.subscribeToSaveResponse(this.foodService.create(food));
    }
  }

  private createFromForm(): IFood {
    return {
      ...new Food(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sn: this.editForm.get(['sn'])!.value,
      thumbnail: this.editForm.get(['thumbnail'])!.value,
      picture: this.editForm.get(['picture'])!.value,
      price: this.editForm.get(['price'])!.value,
      packingFee: this.editForm.get(['packingFee'])!.value,
      desc: this.editForm.get(['desc'])!.value,
      sort: this.editForm.get(['sort'])!.value,
      feedingMode: this.editForm.get(['feedingMode'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFood>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IFoodCategory): any {
    return item.id;
  }
}
