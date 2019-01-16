/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-step-data-sdm-suffix/plan-info-step-data-sdm-suffix-delete-dialog.component';
import { PlanInfoStepDataSdmSuffixService } from 'app/entities/plan-info-step-data-sdm-suffix/plan-info-step-data-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoStepDataSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoStepDataSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoStepDataSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoStepDataSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoStepDataSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataSdmSuffixService);
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
