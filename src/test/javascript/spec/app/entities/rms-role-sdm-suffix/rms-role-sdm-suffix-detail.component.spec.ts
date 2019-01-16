/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsRoleSdmSuffixDetailComponent } from 'app/entities/rms-role-sdm-suffix/rms-role-sdm-suffix-detail.component';
import { RmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsRoleSdmSuffix Management Detail Component', () => {
        let comp: RmsRoleSdmSuffixDetailComponent;
        let fixture: ComponentFixture<RmsRoleSdmSuffixDetailComponent>;
        const route = ({ data: of({ rmsRole: new RmsRoleSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsRoleSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RmsRoleSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsRoleSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rmsRole).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
