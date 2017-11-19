function R = slau()
   J=[1,1,0,0,0;0,1,1,0,0;0,0,1,0,0;0,0,0,2,0;0,0,0,0,3];
   B=rand(5,5);
   while(det(B)==0)
       B=rand(5,5);
   endwhile
   R=inv(B)*J*B;
endfunction