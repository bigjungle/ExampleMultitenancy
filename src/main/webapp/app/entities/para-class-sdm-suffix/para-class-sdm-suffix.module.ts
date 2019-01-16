import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaClassSdmSuffixComponent,
    ParaClassSdmSuffixDetailComponent,
    ParaClassSdmSuffixUpdateComponent,
    ParaClassSdmSuffixDeletePopupComponent,
    ParaClassSdmSuffixDeleteDialogComponent,
    paraClassRoute,
    paraClassPopupRoute
} from './';

const ENTITY_STATES = [...paraClassRoute, ...paraClassPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaClassSdmSuffixComponent,
        ParaClassSdmSuffixDetailComponent,
        ParaClassSdmSuffixUpdateComponent,
        ParaClassSdmSuffixDeleteDialogComponent,
        ParaClassSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaClassSdmSuffixComponent,
        ParaClassSdmSuffixUpdateComponent,
        ParaClassSdmSuffixDeleteDialogComponent,
        ParaClassSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaClassSdmSuffixModule {}
