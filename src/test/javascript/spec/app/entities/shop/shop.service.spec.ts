import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ShopService } from 'app/entities/shop/shop.service';
import { IShop, Shop } from 'app/shared/model/shop.model';
import { OffsetType } from 'app/shared/model/enumerations/offset-type.model';

describe('Service Tests', () => {
  describe('Shop Service', () => {
    let injector: TestBed;
    let service: ShopService;
    let httpMock: HttpTestingController;
    let elemDefault: IShop;
    let expectedResult: IShop | IShop[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ShopService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Shop(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        OffsetType.MARS,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Shop', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.create(new Shop()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Shop', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            sn: 'BBBBBB',
            tel: 'BBBBBB',
            address: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            offsetType: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            shopOpen: 'BBBBBB',
            shopClose: 'BBBBBB',
            maxDeliveryDistance: 1,
            minDeliveryAmount: 1,
            lunchServeStartingAt: 'BBBBBB',
            lunchServeEndAt: 'BBBBBB',
            supperServeStartingAt: 'BBBBBB',
            supperServeEndAt: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Shop', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            sn: 'BBBBBB',
            tel: 'BBBBBB',
            address: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            offsetType: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            shopOpen: 'BBBBBB',
            shopClose: 'BBBBBB',
            maxDeliveryDistance: 1,
            minDeliveryAmount: 1,
            lunchServeStartingAt: 'BBBBBB',
            lunchServeEndAt: 'BBBBBB',
            supperServeStartingAt: 'BBBBBB',
            supperServeEndAt: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Shop', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
