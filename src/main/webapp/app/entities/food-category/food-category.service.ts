import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFoodCategory } from 'app/shared/model/food-category.model';

type EntityResponseType = HttpResponse<IFoodCategory>;
type EntityArrayResponseType = HttpResponse<IFoodCategory[]>;

@Injectable({ providedIn: 'root' })
export class FoodCategoryService {
  public resourceUrl = SERVER_API_URL + 'api/food-categories';

  constructor(protected http: HttpClient) {}

  create(foodCategory: IFoodCategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foodCategory);
    return this.http
      .post<IFoodCategory>(this.resourceUrl, copy, { observe: 'response' }) // observe选项表示返回的是完整响应对象，而不是只有响应体（默认）
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(foodCategory: IFoodCategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foodCategory);
    return this.http
      .put<IFoodCategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFoodCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFoodCategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(foodCategory: IFoodCategory): IFoodCategory {
    const copy: IFoodCategory = Object.assign({}, foodCategory, {
      createdDate: foodCategory.createdDate && foodCategory.createdDate.isValid() ? foodCategory.createdDate.toJSON() : undefined, // toJSON方法会将时间转换为UTC格式字符串
      lastModifiedDate:
        foodCategory.lastModifiedDate && foodCategory.lastModifiedDate.isValid() ? foodCategory.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      // 响应体就是服务响应的IFoodCategory实例
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined; // moment()会将时间转换为本地时区的时间
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((foodCategory: IFoodCategory) => {
        foodCategory.createdDate = foodCategory.createdDate ? moment(foodCategory.createdDate) : undefined;
        foodCategory.lastModifiedDate = foodCategory.lastModifiedDate ? moment(foodCategory.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
