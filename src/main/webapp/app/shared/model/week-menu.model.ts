import { Moment } from 'moment';
import { IWeekStock } from 'app/shared/model/week-stock.model';
import { Week } from 'app/shared/model/enumerations/week.model';

export interface IWeekMenu {
  id?: number;
  week?: Week;
  sort?: number;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
  weekStocks?: IWeekStock[];
  foodId?: number;
}

export class WeekMenu implements IWeekMenu {
  constructor(
    public id?: number,
    public week?: Week,
    public sort?: number,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string,
    public weekStocks?: IWeekStock[],
    public foodId?: number
  ) {}
}
