import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFoodCategory, FoodCategory } from 'app/shared/model/food-category.model';
import { FoodCategoryService } from './food-category.service';

@Component({
  selector: 'jhi-food-category-update',
  templateUrl: './food-category-update.component.html'
})
export class FoodCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(10)]],
    sn: [null, [Validators.required, Validators.maxLength(10)]],
    icon: [null, [Validators.maxLength(30)]],
    sort: [null, [Validators.required, Validators.min(0), Validators.max(999999999)]],
    createdBy: [null, [Validators.required, Validators.maxLength(20)]],
    createdDate: [null, [Validators.required]],
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]]
  });

  constructor(protected foodCategoryService: FoodCategoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ foodCategory }) => {
      if (!foodCategory.id) {
        const today = moment().startOf('day');
        foodCategory.createdDate = today;
        foodCategory.lastModifiedDate = today;
      }

      this.updateForm(foodCategory);
    });
  }

  updateForm(foodCategory: IFoodCategory): void {
    this.editForm.patchValue({
      id: foodCategory.id,
      name: foodCategory.name,
      sn: foodCategory.sn,
      icon: foodCategory.icon,
      sort: foodCategory.sort,
      createdBy: foodCategory.createdBy,
      createdDate: foodCategory.createdDate ? foodCategory.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: foodCategory.lastModifiedDate ? foodCategory.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: foodCategory.lastModifiedBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const foodCategory = this.createFromForm();
    if (foodCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.foodCategoryService.update(foodCategory));
    } else {
      this.subscribeToSaveResponse(this.foodCategoryService.create(foodCategory));
    }
  }

  private createFromForm(): IFoodCategory {
    return {
      ...new FoodCategory(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sn: this.editForm.get(['sn'])!.value,
      icon: this.editForm.get(['icon'])!.value,
      sort: this.editForm.get(['sort'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoodCategory>>): void {
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
}
