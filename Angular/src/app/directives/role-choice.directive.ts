import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appRoleChoice]'
})
export class RoleChoiceDirective {

  isChoosed = false;
  innerText = '';
  previous: ElementRef = null;
  numOfPlayers = 0;

  constructor(private el: ElementRef) {
  }

  @HostListener('click')
  onClick() {
    console.log(this.el.nativeElement);
    if (this.previous === null) {
      this.el.nativeElement.style.border = '1px red solid';
      this.isChoosed = true;
      this.previous = this.el;
      this.numOfPlayers += 1;
    } else if (this.previous === this.el && this.isChoosed === true) {
      this.el.nativeElement.style.border = 'none';
      this.isChoosed = false;
      this.numOfPlayers -= 1;
      this.previous = null;
    } else if (this.previous !== this.el) {
      this.previous.nativeElement.style.border = 'none';
      this.el.nativeElement.style.border = '1px red solid';
      this.numOfPlayers += 1;
      this.isChoosed = true;
      this.previous = this.el;
    }

    console.log(this.numOfPlayers);
  }

}
