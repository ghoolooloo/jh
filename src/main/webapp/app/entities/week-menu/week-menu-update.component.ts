import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWeekMenu, WeekMenu } from 'app/shared/model/week-menu.model';
import { WeekMenuService } from './week-menu.service';
import { IFood } from 'app/shared/model/food.model';
import { FoodService } from 'app/entities/food/food.service';

@Component({
  selector: 'jhi-week-menu-update',
  templateUrl: './week-menu-update.component.html'
})
export class WeekMenuUpdateComponent implements OnInit {
  isSaving = false;
  foods: IFood[] = [];

  editForm = this.fb.group({
    id: [],
    week: [null, [Validators.required]],
    sort: [null, [Validators.required, Validators.min(0), Validators.max(999999999)]],
    lastModifiedDate: [null, [Validators.required]],
    lastModifiedBy: [null, [Validators.required, Validators.maxLength(20)]],
    foodId: []
  });

  constructor(
    protected weekMenuService: WeekMenuService,
    protected foodService: FoodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ weekMenu }) => {
      if (!weekMenu.id) {
        const today = moment().startOf('day');
        weekMenu.lastModifiedDate = today;
      }

      this.updateForm(weekMenu);

      this.foodService.query().subscribe((res: HttpResponse<IFood[]>) => (this.foods = res.body || []));
    });
  }

  updateForm(weekMenu: IWeekMenu): void {
    this.editForm.patchValue({
      id: weekMenu.id,
      week: weekMenu.week,
      sort: weekMenu.sort,
      lastModifiedDate: weekMenu.lastModifiedDate ? weekMenu.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: weekMenu.lastModifiedBy,
      foodId: weekMenu.foodId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const weekMenu = this.createFromForm();
    if (weekMenu.id !== undefined) {
      this.subscribeToSaveResponse(this.weekMenuService.update(weekMenu));
    } else {
      this.subscribeToSaveResponse(this.weekMenuService.create(weekMenu));
    }
  }

  private createFromForm(): IWeekMenu {
    return {
      ...new WeekMenu(),
      id: this.editForm.get(['id'])!.value,
      week: this.editForm.get(['week'])!.value,
      sort: this.editForm.get(['sort'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      foodId: this.editForm.get(['foodId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWeekMenu>>): void {
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

  trackById(index: number, item: IFood): any {
    return item.id;
  }
}
