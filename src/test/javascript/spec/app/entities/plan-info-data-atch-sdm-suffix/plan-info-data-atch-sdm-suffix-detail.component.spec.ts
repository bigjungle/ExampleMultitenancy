/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataAtchSdmSuffixDetailComponent } from 'app/entities/plan-info-data-atch-sdm-suffix/plan-info-data-atch-sdm-suffix-detail.component';
import { PlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataAtchSdmSuffix Management Detail Component', () => {
        let comp: PlanInfoDataAtchSdmSuffixDetailComponent;
        let fixture: ComponentFixture<PlanInfoDataAtchSdmSuffixDetailComponent>;
        const route = ({ data: of({ planInfoDataAtch: new PlanInfoDataAtchSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataAtchSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanInfoDataAtchSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataAtchSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planInfoDataAtch).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
