import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWeekStock, WeekStock } from 'app/shared/model/week-stock.model';
import { WeekStockService } from './week-stock.service';
import { IWeekMenu } from 'app/shared/model/week-menu.model';
import { WeekMenuService } from 'app/entities/week-menu/week-menu.service';

@Component({
  selector: 'jhi-week-stock-update',
  templateUrl: './week-stock-update.component.html'
})
export class WeekStockUpdateComponent implements OnInit {
  isSaving = false;
  weekmenus: IWeekMenu[] = [];

  editForm = this.fb.group({
    id: [],
    shopSN: [null, [Validators.required, Validators.maxLength(30)]],
    shopName: [null, [Validators.required, Validators.maxLength(20)]],
    foodQuantity: [null, [Validators.required, Validators.min(0), Validators.max(99999)]],
    weekMenuId: []
  });

  constructor(
    protected weekStockService: WeekStockService,
    protected weekMenuService: WeekMenuService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ weekStock }) => {
      this.updateForm(weekStock);

      this.weekMenuService.query().subscribe((res: HttpResponse<IWeekMenu[]>) => (this.weekmenus = res.body || []));
    });
  }

  updateForm(weekStock: IWeekStock): void {
    this.editForm.patchValue({
      id: weekStock.id,
      shopSN: weekStock.shopSN,
      shopName: weekStock.shopName,
      foodQuantity: weekStock.foodQuantity,
      weekMenuId: weekStock.weekMenuId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const weekStock = this.createFromForm();
    if (weekStock.id !== undefined) {
      this.subscribeToSaveResponse(this.weekStockService.update(weekStock));
    } else {
      this.subscribeToSaveResponse(this.weekStockService.create(weekStock));
    }
  }

  private createFromForm(): IWeekStock {
    return {
      ...new WeekStock(),
      id: this.editForm.get(['id'])!.value,
      shopSN: this.editForm.get(['shopSN'])!.value,
      shopName: this.editForm.get(['shopName'])!.value,
      foodQuantity: this.editForm.get(['foodQuantity'])!.value,
      weekMenuId: this.editForm.get(['weekMenuId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWeekStock>>): void {
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

  trackById(index: number, item: IWeekMenu): any {
    return item.id;
  }
}
