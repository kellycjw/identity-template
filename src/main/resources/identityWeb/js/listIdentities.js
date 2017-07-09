$( document ).ready(function() {
    listIdentities();
});

 function listIdentities(){
    var newRowContent
    var identity
    $.ajax({
        type: 'GET',
        url: LIST_IDENTITIES_URL,
        success: (result) => {
            result.forEach(function(item, index) {
                identity = item.state.data.identity

                var anexosAppend = "N.A.";

                if(identity.anexos){
                    anexosAppend = "";
                    identity.anexos.forEach(function(anexo){
                        anexosAppend += "<a href='" + DOWNLOAD_DOCUMENT_URL + anexo.secureHash + "/" + anexo.nome + "'>Download</a><br/>";
                    });
                }

                newRowContent = "<tr>"
                newRowContent += "<td>" + identity.nome + "</td>"
                newRowContent += "<td>" + identity.cpf + "</td>"
                newRowContent += "<td>" + identity.documento + "</td>"
                newRowContent += "<td>" + anexosAppend + "</td>";
                newRowContent += "<td>"
                newRowContent += "<a href='updateIdentity.html?cpf=" + identity.cpf + "'>Update</a> | "
                newRowContent += "<a href='deleteIdentity.html?cpf=" + identity.cpf + "'>Delete</a>"
                newRowContent += "</td>"
                newRowContent += "</tr>"

                $(newRowContent).appendTo($("#tbl_identities"));
            });
        },
        error: (errorResult) => {
            showErrorMessage(errorResult);
        }
    });
 }
