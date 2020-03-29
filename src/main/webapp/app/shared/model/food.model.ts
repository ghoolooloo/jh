import { Moment } from 'moment';
import { FeedingMode } from 'app/shared/model/enumerations/feeding-mode.model';

export interface IFood {
  id?: number;
  name?: string;
  sn?: string;
  thumbnail?: string;
  picture?: string;
  price?: number;
  originalPrice?: number;
  cost?: number;
  packingFee?: number;
  desc?: string;
  sort?: number;
  feedingMode?: FeedingMode;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
  categoryId?: number;
}

export class Food implements IFood {
  constructor(
    public id?: number,
    public name?: string,
    public sn?: string,
    public thumbnail?: string,
    public picture?: string,
    public price?: number,
    public originalPrice?: number,
    public cost?: number,
    public packingFee?: number,
    public desc?: string,
    public sort?: number,
    public feedingMode?: FeedingMode,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string,
    public categoryId?: number
  ) {}
}
