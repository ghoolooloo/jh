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
    public latitude?: number
  ) {}
}
