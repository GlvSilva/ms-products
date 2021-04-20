package com.uol.compasso.controller;

import com.uol.compasso.entity.Products;
import com.uol.compasso.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@Api(value="API REST PRODUTOS")
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @ApiOperation(value = "Cria um novo produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna 201 para produtos criados com sucesso."),
            @ApiResponse(code = 400, message = "Retorna 400 para produtos com erro de validação.")
    })
    @PostMapping
    public ResponseEntity<?> salvarProdutos(@RequestBody @Valid Products products) {
        return ResponseEntity.ok(productsService.createProduct(products));
    }

    @ApiOperation(value = "Atualiza dados do produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 para produtos que foram atualizados com sucesso."),
            @ApiResponse(code = 400, message = "Retorna 400 para erros de validação do domínio."),
            @ApiResponse(code = 404, message = "Retorna 404 quando o produto não existe na base de dados.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaProdutos(@PathVariable("id") Long id, @RequestBody Products products) {
        return ResponseEntity.ok().body(productsService.updateProduct(id, products));
    }

    @ApiOperation(value = "Retorna os dados de um produto filtrando pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando o produto foi localizado com sucesso."),
            @ApiResponse(code = 404, message = "Retorna 404 quando o produto não existe na base de dados.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscaProdutosPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(productsService.getProductsById(id));
    }

    @ApiOperation(value = "Recupera os dados de todos os produtos na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando os produtos foram localizados com sucesso (mesmo não havendo produtos).")
    })
    @GetMapping
    public ResponseEntity<?> listaProdutos() {
        return ResponseEntity.ok(productsService.getProducts());
    }

    @ApiOperation(value = "Delete um produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando o produto foi deletado com sucesso."),
            @ApiResponse(code = 404, message = "Retorna 404 quando o produto não existe na base de dados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProdutos(@PathVariable("id") Long id) {
        return productsService.deleteProduct(id);
    }

    @ApiOperation(value = "Busca produtos na base de dados filtrando pelos campos name e description, e pelo intervalo de valor aceitável no campo price.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 para produtos que foram filtrados com sucesso.")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Products>> search(@RequestParam(required = false) String q,
                                                @RequestParam(required = false) BigDecimal min_price,
                                                @RequestParam(required = false) BigDecimal max_price) {
        List<Products> products = this.productsService.search(q, min_price, max_price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
