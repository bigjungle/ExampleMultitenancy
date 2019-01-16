/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepSdmSuffixDetailComponent } from 'app/entities/plan-info-step-sdm-suffix/plan-info-step-sdm-suffix-detail.component';
import { PlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoStepSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoStepSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoStep: new PlanInfoStepSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoStepSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoStep).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
