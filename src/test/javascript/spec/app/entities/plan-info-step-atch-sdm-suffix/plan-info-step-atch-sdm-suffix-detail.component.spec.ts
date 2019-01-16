/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepAtchSdmSuffixDetailComponent } from 'app/entities/plan-info-step-atch-sdm-suffix/plan-info-step-atch-sdm-suffix-detail.component';
import { PlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepAtchSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoStepAtchSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoStepAtchSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoStepAtch: new PlanInfoStepAtchSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepAtchSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoStepAtchSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepAtchSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoStepAtch).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
