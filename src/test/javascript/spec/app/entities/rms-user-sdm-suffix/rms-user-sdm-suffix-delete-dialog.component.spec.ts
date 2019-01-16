/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { RmsUserSdmSuffixDeleteDialogComponent } from 'app/entities/rms-user-sdm-suffix/rms-user-sdm-suffix-delete-dialog.component';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix/rms-user-sdm-suffix.service';

describe('Component Tests', () => {
    describe('RmsUserSdmSuffix Management Delete Component', () => {
        let comp: RmsUserSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<RmsUserSdmSuffixDeleteDialogComponent>;
        let service: RmsUserSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsUserSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(RmsUserSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsUserSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsUserSdmSuffixService);
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
