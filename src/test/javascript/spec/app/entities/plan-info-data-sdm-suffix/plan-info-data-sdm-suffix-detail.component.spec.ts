/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataSdmSuffixDetailComponent } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix-detail.component';
import { PlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoDataSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoDataSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoData: new PlanInfoDataSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoDataSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
