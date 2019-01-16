/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsDepSdmSuffixDetailComponent } from 'app/entities/rms-dep-sdm-suffix/rms-dep-sdm-suffix-detail.component';
import { RmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsDepSdmSuffix Management Detail Component', () => {
        let comp: RmsDepSdmSuffixDetailComponent;
        let fixture: ComponentFixture<RmsDepSdmSuffixDetailComponent>;
        const route = ({ data: of({ rmsDep: new RmsDepSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsDepSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RmsDepSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsDepSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rmsDep).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
