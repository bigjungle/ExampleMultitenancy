/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-step-data-atch-his-sdm-suffix/plan-info-step-data-atch-his-sdm-suffix-delete-dialog.component';
import { PlanInfoStepDataAtchHisSdmSuffixService } from 'app/entities/plan-info-step-data-atch-his-sdm-suffix/plan-info-step-data-atch-his-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoStepDataAtchHisSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoStepDataAtchHisSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataAtchHisSdmSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
