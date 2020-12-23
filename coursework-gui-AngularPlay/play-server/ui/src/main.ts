import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

//*Bootstrap the AppModule defined, so that any component in AppModule is accessed in index.html*//
platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
