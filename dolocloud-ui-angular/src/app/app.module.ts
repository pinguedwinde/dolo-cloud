import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RecentDolosComponent } from './recents/recents.component';
import { ApiService } from './api/ApiService';
import { RecentDolosService } from './recents/RecentDolosService';
import { SpecialsComponent } from './specials/specials.component';

import { routes } from './app.routes';
import { CloudTitleComponent } from './cloud-title/cloudtitle.component';
import { NonAlcoholicsPipe } from './recents/NonAlcoholicPipe';
import { AlcoholicsPipe } from './recents/AlcoholicsPipe';
import { DesignComponent } from './design/design.component';
import { GroupBoxComponent } from './group-box/groupbox.component';
import { BigButtonComponent } from './big-button/bigbutton.component';
import { LittleButtonComponent } from './little-button/littlebutton.component';
import { LocationsComponent } from './locations/locations.component';
import { HttpClientModule } from '@angular/common/http';

import { OAuthModule } from 'angular-oauth2-oidc';
import { CartComponent } from './cart/cart.component';
import { CartService } from './cart/cart-service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    FooterComponent,
    RecentDolosComponent,
    SpecialsComponent,
    LocationsComponent,
    CloudTitleComponent,
    DesignComponent,
    CartComponent,
    NonAlcoholicsPipe,
    AlcoholicsPipe,
    GroupBoxComponent,
    BigButtonComponent,
    LittleButtonComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    OAuthModule.forRoot()
  ],
  providers: [
    ApiService,
    CartService,
    RecentDolosService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
