/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataAtchHisSdmSuffixDetailComponent } from 'app/entities/plan-info-data-atch-his-sdm-suffix/plan-info-data-atch-his-sdm-suffix-detail.component';
import { PlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataAtchHisSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoDataAtchHisSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoDataAtchHisSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoDataAtchHis: new PlanInfoDataAtchHisSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataAtchHisSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoDataAtchHisSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataAtchHisSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoDataAtchHis).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
