/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { ParaClassSdmSuffixDeleteDialogComponent } from 'app/entities/para-class-sdm-suffix/para-class-sdm-suffix-delete-dialog.component';
import { ParaClassSdmSuffixService } from 'app/entities/para-class-sdm-suffix/para-class-sdm-suffix.service';

describe('Component Tests', () => {
    describe('ParaClassSdmSuffix Management Delete Component', () => {
        let comp: ParaClassSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ParaClassSdmSuffixDeleteDialogComponent>;
        let service: ParaClassSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaClassSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(ParaClassSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaClassSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaClassSdmSuffixService);
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
