/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsNodeSdmSuffixDetailComponent } from 'app/entities/rms-node-sdm-suffix/rms-node-sdm-suffix-detail.component';
import { RmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsNodeSdmSuffix Management Detail Component', () => {
        let comp: RmsNodeSdmSuffixDetailComponent;
        let fixture: ComponentFixture<RmsNodeSdmSuffixDetailComponent>;
        const route = ({ data: of({ rmsNode: new RmsNodeSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsNodeSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RmsNodeSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsNodeSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rmsNode).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
