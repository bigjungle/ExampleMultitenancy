import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaCatSdmSuffixComponent,
    ParaCatSdmSuffixDetailComponent,
    ParaCatSdmSuffixUpdateComponent,
    ParaCatSdmSuffixDeletePopupComponent,
    ParaCatSdmSuffixDeleteDialogComponent,
    paraCatRoute,
    paraCatPopupRoute
} from './';

const ENTITY_STATES = [...paraCatRoute, ...paraCatPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaCatSdmSuffixComponent,
        ParaCatSdmSuffixDetailComponent,
        ParaCatSdmSuffixUpdateComponent,
        ParaCatSdmSuffixDeleteDialogComponent,
        ParaCatSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaCatSdmSuffixComponent,
        ParaCatSdmSuffixUpdateComponent,
        ParaCatSdmSuffixDeleteDialogComponent,
        ParaCatSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaCatSdmSuffixModule {}
