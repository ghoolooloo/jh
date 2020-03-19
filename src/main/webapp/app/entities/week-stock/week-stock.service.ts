import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWeekStock } from 'app/shared/model/week-stock.model';

type EntityResponseType = HttpResponse<IWeekStock>;
type EntityArrayResponseType = HttpResponse<IWeekStock[]>;

@Injectable({ providedIn: 'root' })
export class WeekStockService {
  public resourceUrl = SERVER_API_URL + 'api/week-stocks';

  constructor(protected http: HttpClient) {}

  create(weekStock: IWeekStock): Observable<EntityResponseType> {
    return this.http.post<IWeekStock>(this.resourceUrl, weekStock, { observe: 'response' });
  }

  update(weekStock: IWeekStock): Observable<EntityResponseType> {
    return this.http.put<IWeekStock>(this.resourceUrl, weekStock, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWeekStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWeekStock[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
