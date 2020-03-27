import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IShop, Shop } from 'app/shared/model/shop.model';
import { ShopService } from './shop.service';

@Component({
  selector: 'jhi-shop-update',
  templateUrl: './shop-update.component.html'
})
export class ShopUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(20)]],
    sn: [null, [Validators.required, Validators.maxLength(30)]],
    tel: [null, [Validators.maxLength(20)]],
    address: [null, [Validators.maxLength(50)]],
    province: [null, [Validators.required, Validators.maxLength(20)]],
    city: [null, [Validators.required, Validators.maxLength(20)]],
    district: [null, [Validators.maxLength(20)]],
    offsetType: [null, [Validators.required]],
    longitude: [null, [Validators.required]],
    latitude: [null, [Validators.required]],
    shopOpen: [null, [Validators.maxLength(8)]],
    shopClose: [null, [Validators.maxLength(8)]],
    maxDeliveryDistance: [null, [Validators.min(0), Validators.max(50)]],
    minDeliveryAmount: [null, [Validators.min(0), Validators.max(999999)]],
    lunchServeStartingAt: [null, [Validators.maxLength(8)]],
    lunchServeEndAt: [null, [Validators.maxLength(8)]],
    supperServeStartingAt: [null, [Validators.maxLength(8)]],
    supperServeEndAt: [null, [Validators.maxLength(8)]],
    createdBy: [null, [Validators.required, Validators.maxLength(20)]],
    createdDate: [null, [Validators.required]],
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]]
  });

  constructor(protected shopService: ShopService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shop }) => {
      if (!shop.id) {
        const today = moment().startOf('day');
        shop.createdDate = today;
        shop.lastModifiedDate = today;
      }

      this.updateForm(shop);
    });
  }

  updateForm(shop: IShop): void {
    this.editForm.patchValue({
      id: shop.id,
      name: shop.name,
      sn: shop.sn,
      tel: shop.tel,
      address: shop.address,
      province: shop.province,
      city: shop.city,
      district: shop.district,
      offsetType: shop.offsetType,
      longitude: shop.longitude,
      latitude: shop.latitude,
      shopOpen: shop.shopOpen,
      shopClose: shop.shopClose,
      maxDeliveryDistance: shop.maxDeliveryDistance,
      minDeliveryAmount: shop.minDeliveryAmount,
      lunchServeStartingAt: shop.lunchServeStartingAt,
      lunchServeEndAt: shop.lunchServeEndAt,
      supperServeStartingAt: shop.supperServeStartingAt,
      supperServeEndAt: shop.supperServeEndAt,
      createdBy: shop.createdBy,
      createdDate: shop.createdDate ? shop.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: shop.lastModifiedDate ? shop.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: shop.lastModifiedBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shop = this.createFromForm();
    if (shop.id !== undefined) {
      this.subscribeToSaveResponse(this.shopService.update(shop));
    } else {
      this.subscribeToSaveResponse(this.shopService.create(shop));
    }
  }

  private createFromForm(): IShop {
    return {
      ...new Shop(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sn: this.editForm.get(['sn'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      address: this.editForm.get(['address'])!.value,
      province: this.editForm.get(['province'])!.value,
      city: this.editForm.get(['city'])!.value,
      district: this.editForm.get(['district'])!.value,
      offsetType: this.editForm.get(['offsetType'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
      latitude: this.editForm.get(['latitude'])!.value,
      shopOpen: this.editForm.get(['shopOpen'])!.value,
      shopClose: this.editForm.get(['shopClose'])!.value,
      maxDeliveryDistance: this.editForm.get(['maxDeliveryDistance'])!.value,
      minDeliveryAmount: this.editForm.get(['minDeliveryAmount'])!.value,
      lunchServeStartingAt: this.editForm.get(['lunchServeStartingAt'])!.value,
      lunchServeEndAt: this.editForm.get(['lunchServeEndAt'])!.value,
      supperServeStartingAt: this.editForm.get(['supperServeStartingAt'])!.value,
      supperServeEndAt: this.editForm.get(['supperServeEndAt'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShop>>): void {
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
