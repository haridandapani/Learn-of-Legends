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
            <input type="radio" id= ${i}  name="choice" value= ${i} required>
            <label for= ${i}>${choices[i].text}</label><br><br>
          </#list>
      </#if>
      </p>
  <input type="submit" value="Submit Answer">
  </form>
  </div>
</html>