/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { VerifyRecSdmSuffixDeleteDialogComponent } from 'app/entities/verify-rec-sdm-suffix/verify-rec-sdm-suffix-delete-dialog.component';
import { VerifyRecSdmSuffixService } from 'app/entities/verify-rec-sdm-suffix/verify-rec-sdm-suffix.service';

describe('Component Tests', () => {
    describe('VerifyRecSdmSuffix Management Delete Component', () => {
        let comp: VerifyRecSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<VerifyRecSdmSuffixDeleteDialogComponent>;
        let service: VerifyRecSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [VerifyRecSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(VerifyRecSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VerifyRecSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VerifyRecSdmSuffixService);
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
