import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaStateSdmSuffixComponent,
    ParaStateSdmSuffixDetailComponent,
    ParaStateSdmSuffixUpdateComponent,
    ParaStateSdmSuffixDeletePopupComponent,
    ParaStateSdmSuffixDeleteDialogComponent,
    paraStateRoute,
    paraStatePopupRoute
} from './';

const ENTITY_STATES = [...paraStateRoute, ...paraStatePopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaStateSdmSuffixComponent,
        ParaStateSdmSuffixDetailComponent,
        ParaStateSdmSuffixUpdateComponent,
        ParaStateSdmSuffixDeleteDialogComponent,
        ParaStateSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaStateSdmSuffixComponent,
        ParaStateSdmSuffixUpdateComponent,
        ParaStateSdmSuffixDeleteDialogComponent,
        ParaStateSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaStateSdmSuffixModule {}
