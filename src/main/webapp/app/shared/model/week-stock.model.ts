export interface IWeekStock {
  id?: number;
  shopSN?: string;
  shopName?: string;
  foodQuantity?: number;
  weekMenuId?: number;
}

export class WeekStock implements IWeekStock {
  constructor(
    public id?: number,
    public shopSN?: string,
    public shopName?: string,
    public foodQuantity?: number,
    public weekMenuId?: number
  ) {}
}
