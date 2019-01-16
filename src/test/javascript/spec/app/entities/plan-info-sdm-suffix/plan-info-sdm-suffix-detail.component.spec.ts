/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoSdmSuffixDetailComponent } from 'app/entities/plan-info-sdm-suffix/plan-info-sdm-suffix-detail.component';
import { PlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfo: new PlanInfoSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
