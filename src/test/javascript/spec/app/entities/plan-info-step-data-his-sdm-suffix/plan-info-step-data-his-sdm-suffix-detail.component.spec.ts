/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataHisSdmSuffixDetailComponent } from 'app/entities/plan-info-step-data-his-sdm-suffix/plan-info-step-data-his-sdm-suffix-detail.component';
import { PlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataHisSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoStepDataHisSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoStepDataHisSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoStepDataHis: new PlanInfoStepDataHisSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataHisSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoStepDataHisSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataHisSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoStepDataHis).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
