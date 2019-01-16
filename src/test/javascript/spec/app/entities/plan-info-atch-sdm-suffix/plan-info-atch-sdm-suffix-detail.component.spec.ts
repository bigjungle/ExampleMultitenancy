/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoAtchSdmSuffixDetailComponent } from 'app/entities/plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix-detail.component';
import { PlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoAtchSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoAtchSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoAtchSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoAtch: new PlanInfoAtchSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoAtchSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoAtchSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoAtchSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoAtch).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
