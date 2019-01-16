/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataAtchSdmSuffixDetailComponent } from 'app/entities/plan-info-step-data-atch-sdm-suffix/plan-info-step-data-atch-sdm-suffix-detail.component';
import { PlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataAtchSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoStepDataAtchSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoStepDataAtchSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoStepDataAtch: new PlanInfoStepDataAtchSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataAtchSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoStepDataAtchSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataAtchSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoStepDataAtch).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
