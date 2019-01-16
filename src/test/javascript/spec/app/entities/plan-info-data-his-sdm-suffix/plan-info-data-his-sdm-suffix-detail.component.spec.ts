/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataHisSdmSuffixDetailComponent } from 'app/entities/plan-info-data-his-sdm-suffix/plan-info-data-his-sdm-suffix-detail.component';
import { PlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataHisSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoDataHisSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoDataHisSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoDataHis: new PlanInfoDataHisSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataHisSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoDataHisSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataHisSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoDataHis).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
