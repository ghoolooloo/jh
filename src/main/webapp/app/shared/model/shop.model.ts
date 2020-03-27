import { Moment } from 'moment';
import { OffsetType } from 'app/shared/model/enumerations/offset-type.model';

export interface IShop {
  id?: number;
  name?: string;
  sn?: string;
  tel?: string;
  address?: string;
  province?: string;
  city?: string;
  district?: string;
  offsetType?: OffsetType;
  longitude?: number;
  latitude?: number;
  shopOpen?: string;
  shopClose?: string;
  maxDeliveryDistance?: number;
  minDeliveryAmount?: number;
  lunchServeStartingAt?: string;
  lunchServeEndAt?: string;
  supperServeStartingAt?: string;
  supperServeEndAt?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
}

export class Shop implements IShop {
  constructor(
    public id?: number,
    public name?: string,
    public sn?: string,
    public tel?: string,
    public address?: string,
    public province?: string,
    public city?: string,
    public district?: string,
    public offsetType?: OffsetType,
    public longitude?: number,
    public latitude?: number,
    public shopOpen?: string,
    public shopClose?: string,
    public maxDeliveryDistance?: number,
    public minDeliveryAmount?: number,
    public lunchServeStartingAt?: string,
    public lunchServeEndAt?: string,
    public supperServeStartingAt?: string,
    public supperServeEndAt?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string
  ) {}
}
