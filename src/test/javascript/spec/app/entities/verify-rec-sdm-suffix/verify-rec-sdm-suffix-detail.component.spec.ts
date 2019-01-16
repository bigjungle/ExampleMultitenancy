/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { VerifyRecSdmSuffixDetailComponent } from 'app/entities/verify-rec-sdm-suffix/verify-rec-sdm-suffix-detail.component';
import { VerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';

describe('Component Tests', () => {
    describe('VerifyRecSdmSuffix Management Detail Component', () => {
        let comp: VerifyRecSdmSuffixDetailComponent;
        let fixture: ComponentFixture<VerifyRecSdmSuffixDetailComponent>;
        const route = ({ data: of({ verifyRec: new VerifyRecSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [VerifyRecSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VerifyRecSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VerifyRecSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.verifyRec).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
