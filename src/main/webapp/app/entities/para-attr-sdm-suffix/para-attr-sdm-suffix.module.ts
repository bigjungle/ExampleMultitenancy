import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaAttrSdmSuffixComponent,
    ParaAttrSdmSuffixDetailComponent,
    ParaAttrSdmSuffixUpdateComponent,
    ParaAttrSdmSuffixDeletePopupComponent,
    ParaAttrSdmSuffixDeleteDialogComponent,
    paraAttrRoute,
    paraAttrPopupRoute
} from './';

const ENTITY_STATES = [...paraAttrRoute, ...paraAttrPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaAttrSdmSuffixComponent,
        ParaAttrSdmSuffixDetailComponent,
        ParaAttrSdmSuffixUpdateComponent,
        ParaAttrSdmSuffixDeleteDialogComponent,
        ParaAttrSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaAttrSdmSuffixComponent,
        ParaAttrSdmSuffixUpdateComponent,
        ParaAttrSdmSuffixDeleteDialogComponent,
        ParaAttrSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaAttrSdmSuffixModule {}
