<html>
<link rel="stylesheet" href="/css/style.css">
<title>Question</title>
  <div class  = "column">
      <p class = "question"> ${question}</p>
      <br>
      <form method="POST" action="/answer">
      <p>
        <#if choices??>
          <#list 0..choices?size-1 as i>
            <div class = "radio-item">
              <input  type="radio" id= ${i}  name="choice" value= ${i} required>
              <label for= ${i}>${choices[i].text}</label><br><br>
            </div>
          </#list>
      </#if>
      </p>
  <input class = "button" type="submit" value="Submit Answer">
  </form>
  </div>
</html>