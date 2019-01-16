/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataAtchHisSdmSuffixDetailComponent } from 'app/entities/plan-info-step-data-atch-his-sdm-suffix/plan-info-step-data-atch-his-sdm-suffix-detail.component';
import { PlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataAtchHisSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoStepDataAtchHisSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoStepDataAtchHisSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoStepDataAtchHis: new PlanInfoStepDataAtchHisSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataAtchHisSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoStepDataAtchHisSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataAtchHisSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoStepDataAtchHis).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
