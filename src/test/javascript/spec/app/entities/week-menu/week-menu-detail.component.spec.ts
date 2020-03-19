import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhTestModule } from '../../../test.module';
import { WeekMenuDetailComponent } from 'app/entities/week-menu/week-menu-detail.component';
import { WeekMenu } from 'app/shared/model/week-menu.model';

describe('Component Tests', () => {
  describe('WeekMenu Management Detail Component', () => {
    let comp: WeekMenuDetailComponent;
    let fixture: ComponentFixture<WeekMenuDetailComponent>;
    const route = ({ data: of({ weekMenu: new WeekMenu(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [WeekMenuDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WeekMenuDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WeekMenuDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load weekMenu on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.weekMenu).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
