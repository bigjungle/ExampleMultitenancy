/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsUserSdmSuffixDetailComponent } from 'app/entities/rms-user-sdm-suffix/rms-user-sdm-suffix-detail.component';
import { RmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsUserSdmSuffix Management Detail Component', () => {
        let comp: RmsUserSdmSuffixDetailComponent;
        let fixture: ComponentFixture<RmsUserSdmSuffixDetailComponent>;
        const route = ({ data: of({ rmsUser: new RmsUserSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsUserSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RmsUserSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsUserSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rmsUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
