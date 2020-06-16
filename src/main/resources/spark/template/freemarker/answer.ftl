<html>
<link rel="stylesheet" href="/css/style.css">
<title>Answer</title>
  <div class  = "column">
      <p class = "question"> ${question}</p>
      <br>
    <p>
      <#if choices??>
        <#list 0..choices?size-1 as i>
            <#if choices[i].correct>
                <span style="background-color:#90ee90">${choices[i].text}</span>
            <#elseif i == submitted && !choices[i].correct>
                <span style="background-color:#ffcccb">${choices[i].text}</span>
            <#else>
                <span>${choices[i].text}</span>
            </#if>
            <br><br>
        </#list>
    </#if>
    </p>
  <a href="/question" class="button">Next</a>
  </div>
</html>