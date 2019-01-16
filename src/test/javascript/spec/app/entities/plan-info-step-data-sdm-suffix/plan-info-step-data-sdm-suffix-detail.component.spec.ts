/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataSdmSuffixDetailComponent } from 'app/entities/plan-info-step-data-sdm-suffix/plan-info-step-data-sdm-suffix-detail.component';
import { PlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoStepDataSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoStepDataSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoStepData: new PlanInfoStepDataSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoStepDataSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoStepData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
