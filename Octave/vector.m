function R = vector(A)
    [Q,S]=schur(A);
    S
    N=size(S);
    V=zeros(1,N);
    for i=1:N
        V(1,i)=S(i,i);
    endfor
    
    R=zeros(N,N);
 eps=10^(-2);
 status=0;
    for i=1:N
      for p=1:i-1
if((((V(1,p)>=V(1,i))&&((V(1,p)-eps)<V(1,i)))||((V(1,p)<V(1,i))&&((V(1,p)+eps>V(1,i)))))&&(i>1))
                 R(:,i)=R(:,p);
                status=1;
                 break;
             endif
         
         
      endfor
      if(status==0)
        for j=N:-1:1
            if(j>i)
                R(j,i)=0;
            elseif(j==i)
                R(j,i)=1;
        else
            for k=N:-1:j+1
                R(j,i)=R(j,i)+S(j,k)*R(k,i);
            endfor
            temp=V(1,i)-S(j,j);
            R(j,i)=R(j,i)./temp;
        endif
        endfor
    endif
    status=0;
    endfor
    for i=1:N
        if(R(:,i)'*R(:,i)!=1)
            norm=R(:,i)'*R(:,i);
            norm=sqrt(norm);
            R(:,i)=R(:,i)./norm;
        endif
    endfor
    R=Q*R;
            
endfunction
        
            
        